<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="package text-center">
    <div class="container">
        <h3>Book A Room</h3>
        <p>Book unique homes and experience a city like a local.</p>
        <!-- requried-jsfiles-for owl -->
        <link href="${pageContext.request.contextPath}/css/owl.carousel.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/owl.carousel.js"></script>
        <script>
            $(document).ready(function () {
                $("#owl-demo").owlCarousel({
                    items: 1,
                    lazyLoad: true,
                    autoPlay: true,
                    navigation: true,
                    navigationText: false,
                    pagination: false,
                });
            });
        </script>
        <!-- //requried-jsfiles-for owl -->
        <div id="owl-demo" class="owl-carousel">
            <div class="item text-center image-grid">	
                <ul>
                    <li><img src="${pageContext.request.contextPath}/images/1.jpg" alt=""></li>			    
                    <li><img src="${pageContext.request.contextPath}/images/2.jpg" alt=""></li>				 
                    <li><img src="${pageContext.request.contextPath}/images/3.jpg" alt=""></li>
                </ul>
            </div>
            <div class="item text-center image-grid">	
                <ul>
                    <li> <img src="${pageContext.request.contextPath}/images/3.jpg" alt=""></li>			    
                    <li><img src="${pageContext.request.contextPath}/images/4.jpg" alt=""></li>				 
                    <li><img src="${pageContext.request.contextPath}/images/5.jpg" alt=""></li>
                </ul>
            </div>
            <div class="item text-center image-grid">	
                <ul>
                    <li> <img src="${pageContext.request.contextPath}/images/6.jpg" alt=""></li>			    
                    <li><img src="${pageContext.request.contextPath}/images/2.jpg" alt=""></li>				 
                    <li><img src="${pageContext.request.contextPath}/images/8.jpg" alt=""></li>
                </ul>
            </div>
        </div> 		
    </div>
</div>