package com.example.demo.Controllers;

import com.example.demo.ShopItem;
import com.example.demo.ShopItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ShopItemController {

    @Autowired
    private ShopItemData shopItemData;

    @RequestMapping(value = "/webshop", method = RequestMethod.GET)
    private String getShopItemList(Model model) {
        List<ShopItem> shopItemList = shopItemData.findAll();

        model.addAttribute("shopItemList", shopItemList);

        return "webshop";
    }

    @RequestMapping(value = "/only-available", method = RequestMethod.GET)
    private String getAvailableItem(Model model) {
        List<ShopItem> shopItemList = shopItemData.findAll();
        for (int i = 0; i < shopItemList.size(); i++) {
            if (shopItemList.get(i).getQuantity() == 0) {
                shopItemList.remove(i);
            }
        }

        model.addAttribute("shopItemList", shopItemList);

        return "webshop";
    }

    @RequestMapping(value = "/cheapest-first", method = RequestMethod.GET)
    private String cheapestFirst(Model model) {

        class ShopItemPriceComperator implements Comparator<ShopItem> {
            public int compare(ShopItem s1, ShopItem s2) {
                if (s1.getPrice() == s2.getPrice())
                    return 0;
                else if (s1.getPrice() > s2.getPrice())
                    return 1;
                else
                    return -1;
            }
        }

        List<ShopItem> shopItemList = shopItemData.findAll();

        Collections.sort(shopItemList, new ShopItemPriceComperator());

        model.addAttribute("shopItemList", shopItemList);

        return "webshop";
    }

    @RequestMapping(value = "/contains-nike", method = RequestMethod.GET)
    private String containsNike(Model model) {
        List<ShopItem> shopItemList = shopItemData.findAll();
        List<ShopItem> result = new ArrayList<>();

        String wordToLookFor = "Nike";

        for (int i = 0; i < shopItemList.size(); i++) {
            if (shopItemList.get(i).getDescription().contains(wordToLookFor)) {
                result.add(shopItemList.get(i));
            }
        }

        model.addAttribute("shopItemList", result);

        return "webshop";
    }

    @RequestMapping(value = "/average-stock", method = RequestMethod.GET)
    private String averageStock(Model model) {
        List<ShopItem> shopItemList = shopItemData.findAll();

        double average = 0;
        int count = 0;
        String result;


        for (int i = 0; i < shopItemList.size(); i++) {
            average += shopItemList.get(i).getQuantity();
            count++;
        }

        average /= count;
        result = "The average stock is: " + String.valueOf(average) + ".";

        model.addAttribute("result", result);

        return "averageStock";
    }

    @RequestMapping(value = "/most-expensive-item", method = RequestMethod.GET)
    private String mostExpensiveItem(Model model) {
        List<ShopItem> shopItemList = shopItemData.findAll();

        double highestPrice;
        String mostExpensive = new String();
        String result;


        for (int i = 0; i < shopItemList.size(); i++) {
            highestPrice = shopItemList.get(0).getPrice();
            mostExpensive = shopItemList.get(0).getName();
            if (shopItemList.get(i).getPrice() > highestPrice) {
                highestPrice = shopItemList.get(i).getPrice();
                mostExpensive = shopItemList.get(i).getName();
            }

        }
        result = "The most expensive item is:\r\n" + mostExpensive + ".";
        model.addAttribute("result", result);

        return "averageStock";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private String search(@RequestParam(required = false) String text, Model model) {
        List<ShopItem> shopItemList = shopItemData.findAll();

        String searchFor = text;
        List<ShopItem> result = new ArrayList<>();

        for (int i = 0; i < shopItemList.size(); i++) {
            if (shopItemList.get(i).getDescription().contains(searchFor)
                    || shopItemList.get(i).getName().contains(searchFor)) {
                result.add(shopItemList.get(i));
            }
        }

        model.addAttribute("shopItemList", result);

        return "webshop";
    }
}
