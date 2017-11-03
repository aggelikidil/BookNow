package dao;

import entities.OfferPojo;
import entities.RatingPojo;
import entities.RoomDetailsPojo;
import entities.RoomPojo;
import entities_collaboration.UserRateProfilePojo;
import entities_collaboration.UserRateVisitPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecommendationDaoImpl extends RoomDaoImpl implements RecommendationDao {

    private static final int LIMIT = 5;

    public RecommendationDaoImpl(boolean pool) {
        super(pool);
    }

    private static final String SQL_R1 = "select user_rate as UserID, avg(value) as Average, count(*) as TotalRates from Rating group by user_rate";
    private static final String SQL_R2 = "select r1.user_rate as user_id, r1.room_id, r1.value as RateValue, r2.value as VisitedValue from Rating r1"
            + "	left outer join User_has_visited_Room r2 on r1.user_rate = r2.User_id"
            + "union"
            + "select r1.user_rate as user_id, r1.room_id, r1.value as RateValue, r2.value as VisitedValue from Rating r1"
            + "	right outer join User_has_visited_Room r2 on r1.user_rate = r2.User_id";

    private static UserRateProfilePojo mapUserRateProfilePojo(ResultSet resultSet) throws SQLException {
        UserRateProfilePojo pojo = new UserRateProfilePojo();
        pojo.setId(resultSet.getLong("UserID"));
        pojo.setAvg_rate(resultSet.getDouble("Average"));
        pojo.setTotal_rates(resultSet.getLong("TotalRates"));

        return pojo;
    }

    private static UserRateVisitPojo mapUserRateVisitPojo(ResultSet resultSet) throws SQLException {
        UserRateVisitPojo pojo = new UserRateVisitPojo();
        pojo.setId(resultSet.getLong("user_id"));
        pojo.setRoom_id(resultSet.getLong("room_id"));
        pojo.setRate(resultSet.getDouble("RateValue"));
        pojo.setVisited_value(resultSet.getLong("VisitedValue"));

        return pojo;
    }

    private List<UserRateProfilePojo> listUserRateProfile() {
        List<UserRateProfilePojo> user_profile = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_R1);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserRateProfilePojo obj = mapUserRateProfilePojo(resultSet);
                user_profile.add(obj);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return user_profile;
    }

    private List<UserRateVisitPojo> listUserRateVisit() {
        List<UserRateVisitPojo> user_rate_visit = new ArrayList<>();

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_R2);
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                UserRateVisitPojo room = mapUserRateVisitPojo(resultSet);
                user_rate_visit.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return user_rate_visit;
    }

    // https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
    private LinkedHashMap<Long, Double> sortHashMapByValues(HashMap<Long, Double> passedMap) {
        List<Long> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Double> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        Collections.reverse(mapValues);
        Collections.reverse(mapKeys);

        LinkedHashMap<Long, Double> sortedMap = new LinkedHashMap<>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<Long> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Long key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    // fetch top 5 rooms
    @Override
    public List<RoomPojo> list(Long user_id) {
        UserDao daoU = new UserDaoImpl(true);
        RoomDao daoR = new RoomDaoImpl(true);
        RatingDao daoRt = new RatingDaoImpl(true);

        List<RoomPojo> recommendations = new ArrayList<>();
        List<UserRateProfilePojo> R1 = listUserRateProfile();
        List<UserRateVisitPojo> R2 = listUserRateVisit();

        List<Long> user_ids = daoU.listIDs();
        List<Long> room_ids = daoR.listIDs();

        int n = user_ids.size();
        int m = room_ids.size();
        Double[][] M = new Double[n + 1][m + 1];

        // allocate matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                M[i][j] = null;
            }
        }

        // initialize non null values of matrix        
        for (UserRateProfilePojo p : R1) {
            if (p.getTotal_rates() == 0) { // initialize by visits
                double max = 0;
                for (UserRateVisitPojo r : R2) {
                    if (r.getId().equals(p.getId())) {
                        if (r.getVisited_value() != null) {
                            int i = p.getId().intValue();
                            int j = r.getRoom_id().intValue();

                            M[i][j] = r.getVisited_value().doubleValue();

                            if (M[i][j] > max) {
                                max = M[i][j];
                            }
                        }
                    }
                }

                int i = p.getId().intValue();
                if (max > 0) {
                    for (int j = 1; i <= m; j++) {
                        M[i][j] = M[i][j] / max * 10;
                    }
                }

            } else {                    // initialize by ratings
                List<RatingPojo> R3 = daoRt.find_by_user_id(p.getId());

                for (RatingPojo r : R3) {
                    int i = p.getId().intValue();
                    int j = r.getRoom_id().intValue();

                    M[i][j] = r.getValue().doubleValue();
                }
            }
        }

        // normalization
        for (int i = 1; i <= n; i++) {
            double avg = 0;
            int d = 0;

            for (int j = 1; j <= m; j++) {
                if (M[i][j] != null) {
                    avg += M[i][j];
                    d++;
                }
            }
            if (d != 0) {
                avg /= d;
            }

            for (int j = 1; j <= m; j++) {
                if (M[i][j] != null && i != user_id) {
                    M[i][j] -= avg;
                }
            }
        }

        // for each room, not visited or rated by customer...
        int i = user_id.intValue();

        // map: room_id => estimatedScore for customer
        HashMap<Long, Double> estimatedScore = new HashMap<>();

        for (int j = 1; j <= m; j++) {
            if (M[i][j] == null) { // candidate for advertisement
                double score = 0;

                for (int k = 1; k <= n; k++) { // for each neighbor ...
                    if (k == i) {
                        continue;
                    }
                    Double neighbors_rate = M[k][j];

                    if (neighbors_rate == null) {
                        neighbors_rate = 0.0;
                    }

                    // similarity: user i with user k
                    double sim = 0;
                    double norm_ui = 0;
                    double norm_uk = 0;

                    for (int jj = 1; jj <= m; jj++) {
                        Double r_ui = M[i][jj];
                        Double r_uk = M[k][jj];

                        if (r_ui == null) {
                            r_ui = 0.0;
                        }
                        if (r_uk == null) {
                            r_uk = 0.0;
                        }

                        sim = sim + r_ui * r_uk;
                        norm_ui = norm_ui + (r_ui) * (r_ui);
                        norm_uk = norm_uk + (r_uk) * (r_uk);
                    }

                    if (norm_ui != 0 && norm_uk != 0) {
                        sim = sim / (Math.sqrt(norm_ui) * Math.sqrt(norm_uk));
                    }

                    double amount = sim * neighbors_rate;
                    score += amount;
                }

                estimatedScore.put(new Long(j), score);
            }
        }

        // sort
        LinkedHashMap<Long, Double> sortHashMapByValues = sortHashMapByValues(estimatedScore);

        // traverse and keep top 5
        //https://stackoverflow.com/questions/12310914/how-to-iterate-through-linkedhashmap-with-lists-as-valuesD
        int counter = 0;

        RoomDetailsDao rddao = new RoomDetailsDaoImpl(true);
        OfferDao ofdao = new OfferDaoImpl(true);

        for (Map.Entry<Long, Double> entry : sortHashMapByValues.entrySet()) {
            Long room_id = entry.getKey();
            RoomPojo room = daoR.recfind(room_id);

            RoomDetailsPojo details = rddao.find(room.getRoom_details());
            room.setDetails(details);

            OfferPojo offerdetails = ofdao.find(room.getId());
            room.setOfferdetails(offerdetails);

            recommendations.add(room);
            counter++;
            if (counter > LIMIT) {
                break;
            }
        }

        // find all rooms with DAO and to list
        return recommendations;
    }

}
