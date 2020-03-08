package id.welen.demo.controller;

import id.welen.demo.dao.Order;
import id.welen.demo.dao.Orders;
import id.welen.demo.dao.Status;
import id.welen.demo.util.UrlShortner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class HelloController {

    @Autowired
    UrlShortner urlShortner;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public Orders getResponseJson() {

        Status status1 = new Status(3, "已取消");
        Order order1 = new Order("Livi優活 抽取式衛生紙(100抽x10包x10串/箱)", "https://static.oopocket.com/store/iconTreemall@3x.png",
                status1, "107/6/12");

        Status status2 = new Status(2, "已成立");
        Order order2 = new Order("BALMUDA The Toaster 百慕達烤麵包機-黑色", "https://static.oopocket.com/store/iconTreemall@3x.png",
                status2, "108/7/21");

        Status status3 = new Status(1, "處理中");
        Order order3 = new Order("贈-短慧萬用鍋HD2133+三合一濾網「LG樂金」韓國原裝...", "https://static.oopocket.com/store/iconTreemall@3x.png",
                status3, "108/6/2");

        Status status4 = new Status(4, "已送達");
        Order order4 = new Order("Apple AirPds 2", "https://static.oopocket.com/store/iconTreemall@3x.png",
                status4, "108/3/02");

        Collection<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        return new Orders(orders);
    }

    @RequestMapping(value = "/tinyUrl", method = RequestMethod.POST)
    public String createTinyUrl(@RequestParam("url") String url) {
        System.out.println("url ->" + url);
        if (StringUtils.isEmpty(url)) {
            return "empty url";
        } else {
            return "http://localhost:8080/tinyUrl/" + urlShortner.getShortURL(url);
        }
    }

    @RequestMapping(value = "/tinyUrl/{shortUrl}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) {
        System.out.println("short url ->" + shortUrl);
        if (!StringUtils.isEmpty(shortUrl)) {
            String originalUrl = urlShortner.getOriginalURL(shortUrl);
            if (!StringUtils.isEmpty(originalUrl)) {
                httpServletResponse.setHeader("Location", originalUrl);
                httpServletResponse.setStatus(302);
            }
        }
    }
}
