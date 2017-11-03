<%-- 
    Document   : menubar
    Created on : Jul 20, 2017, 1:31:03 PM
    Author     : aggeliki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-8 booking-form">
    <form action="${pageContext.request.contextPath}/booking/booking_ok" method="POST">
        <h5><b>NAME</b></h5>
        <input type="text" value="">
        <br/>
        <h5><b>E-MAIL</b></h5>
        <input type="text" value="">
        <br/>
        <h5><b>PHONE</b></h5>
        <input type="text" value="">
        <br/>
        <h5><b>TIME</b></h5>
        <input type="text" value="" class="time">
        <br/>
        <h5><b>CHECK IN</b></h5>
        <select class="arrival">
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
            <option>31</option>					 
        </select>
        <select class="arrival">
            <option>Jan</option>
            <option>Feb</option>
            <option>Mar</option>
            <option>Apr</option>
            <option>May</option>
            <option>June</option>
            <option>July</option>
            <option>Aug</option>
            <option>Sep</option>
            <option>Oct</option>
            <option>Nov</option>					 
            <option>Dec</option>
        </select>
        <select class="arrival">
            <option>2012</option>
            <option>2013</option>
            <option>2014</option>
            <option>2015</option>
            <option>2016</option>
            <option>2017</option>
        </select>
        <br/>
        <br/>
        <h5><b>CHECK OUT</b></h5>
        <select class="arrival">
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
            <option>31</option>					 
        </select>
        <select class="arrival">
            <option>Jan</option>
            <option>Feb</option>
            <option>Mar</option>
            <option>Apr</option>
            <option>May</option>
            <option>June</option>
            <option>July</option>
            <option>Aug</option>
            <option>Sep</option>
            <option>Oct</option>
            <option>Nov</option>					 
            <option>Dec</option>
        </select>
        <select class="arrival">
            <option>2012</option>
            <option>2013</option>
            <option>2014</option>
            <option>2015</option>
            <option>2016</option>
            <option>2017</option>
        </select>
        <br/>
        <br/>
        <h5 class="mem"><b>MEMBERS</b></h5>
        <input min="1" type="number" id="quantity" name="quantity" value="1" class="form-control input-small">
        <br/>
        <h5><b>REQUIRED</b></h5>
        <textarea value=""></textarea>
        <br/>


        <input type="submit" value="Submit">
    </form>

    <form action="${pageContext.request.contextPath}/booking" method="POST">
        <input type="reset" value="Reset">
    </form>

</div>