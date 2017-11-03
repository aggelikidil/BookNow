<%-- 
    Document   : menubar
    Created on : Jul 20, 2017, 1:31:03 PM
    Author     : aggeliki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/js_fragments/datepicker.jsp"/>    

<div class="online_reservation">
    <div class="b_room">
        <div class="booking_room">
            <div class="reservation">
                <ul>				
                    <li  class="span1_of_1 left">
                        <h5>Arrival</h5>
                        <div class="book_date">
                            <form>
                                <input class="date" id="datepicker" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = '';
                                        }">
                            </form>
                        </div>					
                    </li>
                    <li  class="span1_of_1 left">
                        <h5>Depature</h5>
                        <div class="book_date">
                            <form>
                                <input class="date" id="datepicker1" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = '';
                                        }">
                            </form>
                        </div>		
                    </li>
                    <li class="span1_of_1">
                        <h5>Room type</h5>
                        <!----------start section_room----------->
                        <div class="section_room">
                            <select id="country" onchange="change_country(this.value)" class="frm-field required">
                                <option value="AX">Studio apartment</option>
                                <option value="AX">Maisonette</option>         
                                <option value="AX">Loft</option>
                                <option value="AX">Railroad</option>
                                <option value="AX">Garden apartment</option>
                                <option value="AX">Bungalow</option>
                            </select>
                        </div>	
                    </li>
                    <li class="span1_of_3">
                        <div class="date_btn">
                            <form>
                                <input type="submit" value="View Prices" />
                            </form>
                        </div>
                    </li>
                    <div class="clearfix"></div>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>