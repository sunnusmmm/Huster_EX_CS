package com.nhy.demo.mall.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nhy.demo.mall.entity.Order;
import com.nhy.demo.mall.entity.OrderItem;
import com.nhy.demo.mall.entity.pojo.ResultBean;
import com.nhy.demo.mall.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    //打开订单列表页面
    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/order/list";
    }

    // 获取所有订单的总数
    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = PageRequest.of(1, 15);
        int total = (int) orderService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    //获取所有订单
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Order>> listData(int pageindex, @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = PageRequest.of(pageindex, pageSize);
        List<Order> list = orderService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    //获取订单项
    @ResponseBody
    @RequestMapping("/getDetail.do")
    public ResultBean<List<OrderItem>> getDetail(int orderId) {
        List<OrderItem> list = orderService.findItems(orderId);
        return new ResultBean<>(list);
    }

    //发货
    @ResponseBody
    @RequestMapping("/send.do")
    public ResultBean<Boolean> send(int id) {
        orderService.updateStatus(id, 3);
        return new ResultBean<>(true);
    }
}
