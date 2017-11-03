<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/js_fragments/datepicker_raw.jsp"/> 


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-8 booking-form">			 
    <a href="${pageContext.request.contextPath}/search/results/details?id=${id}"><h5><i><u>Here you can see the room</u></i></h5></a>

    <form action="${pageContext.request.contextPath}/rentings/create" method="POST">
        
         <h5>AVAILABLE PERIOD</h5>
        
         <ul>        
            <h5>DATE FROM</h5>
            <div class="book_date">
                <input class="date" placeholder="select from date" id="datepicker" name="from" type="text" value="${OFFER.date_from}" onfocus="this.value = '';" onblur="if (this.value == '')  {
                            this.value = '${OFFER.date_from}' readonly="true" disabled="true";
                        }">
            </div>
            <br/>
            <h5>DATE TO</h5>
            <div class="book_date">
                <input class="date" placeholder="select to date" id="datepicker1" name="to" type="text" value="${OFFER.date_to}" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '${OFFER.date_to}'  readonly="true" disabled="true";
                        }">
            </div>
            <br/>
            <input type="hidden" name="id" value="${id}" />
        </ul>
                
        
        <h5>RENT PERIOD</h5>
        
        <ul>        
            <h5>DATE FROM</h5>
            <div class="book_date">
                <input class="date" placeholder="select from date" id="datepicker" name="from" type="text" value="${from}" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '';
                        }">
            </div>					
            <h5>DATE TO</h5>
            <div class="book_date">
                <input class="date" placeholder="select to date" id="datepicker1" name="to" type="text" value="${to}" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '';
                        }">
            </div>
            <input type="hidden" name="id" value="${id}" />
        </ul>

       

        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>			      
</div>