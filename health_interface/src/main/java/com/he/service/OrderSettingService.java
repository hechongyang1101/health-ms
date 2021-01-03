package com.he.service;

import com.he.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    public void add(List<OrderSetting> orderSettingList);

    public List<Map> getOrderSettingByMonth(String date);

    public void editNumberByDate(OrderSetting orderSetting);
}
