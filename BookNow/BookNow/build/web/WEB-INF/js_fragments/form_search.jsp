<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/js_fragments/datepicker.jsp"/>    

<div class="online_reservation">
    <div class="b_room">
        <div class="booking_room">            
            <div class="reservation">                
                <form action="${pageContext.request.contextPath}/search" method="POST">
                    <ul>				

                        <li  class="span1_of_1 left">
                            <h5>Check in</h5>
                            <div class="book_date">
                                <input class="date" id="datepicker" name="from" value="${from}" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = '';
                                        }">
                            </div>					
                        </li>
                        <li  class="span1_of_1 left">
                            <h5>Check out</h5>
                            <div class="book_date">
                                <input class="date" id="datepicker1" name="to" value="${to}" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = '';
                                        }">
                            </div>		
                        </li>
                        <li class="span1_of_2 left">
                            <h5>Members</h5>
                            <!----------start section_room----------->
                            <div class="section_room">
                                <select id="country" name="members" onchange="change_country(this.value)" class="frm-field required">
                                    <option value="1">1</option>
                                    <option value="2">2</option>         
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                </select>
                            </div>	
                        </li>
                        <li class="span1_of_1">
                            <h5>Location / description</h5>
                            <div class="section_room">
                                <input size="16" name="location" type="text" value="">
                            </div>	
                        </li>                                             
                    </ul>

                    <ul>
                        <li class="span1_of_2 ">
                            <div class="date_btn">
                                <input type="submit" value="Search" />
                            </div>
                        </li>
                        <div class="clearfix"></div>
                    </ul>
                </form>

                <ul>
                    <div class="date_btn">
                        <form action="${pageContext.request.contextPath}/search/advanced_search" method="GET">
                            <input type="submit" value="Advanced"/>
                        </form>
                    </div>
                </ul>

            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
