package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopItemData {

    ShopItem RunningShoes = new ShopItem();
    ShopItem Printer = new ShopItem();
    ShopItem CocaCola  = new ShopItem();
    ShopItem Wokin = new ShopItem();
    ShopItem TShirt = new ShopItem();

    public List<ShopItem> findAll() {

        ArrayList<ShopItem> shopItemList = new ArrayList<>();

        RunningShoes.setName("Running Shoes");
        RunningShoes.setDescription("Nike running shoes for every day sport");
        RunningShoes.setPrice(1000.0);
        RunningShoes.setQuantity(5);

        Printer.setName("Printer");
        Printer.setDescription("Some HP printer that will print pages");
        Printer.setPrice(3000.0);
        Printer.setQuantity(2);

        CocaCola.setName("Coca Cola");
        CocaCola.setDescription("0.5 standard coke");
        CocaCola.setPrice(25.0);
        CocaCola.setQuantity(0);

        Wokin.setName("Wokin");
        Wokin.setDescription("Chicken with fried rice and WOKIN sauce");
        Wokin.setPrice(119.0);
        Wokin.setQuantity(100);

        TShirt.setName("T-shirt");
        TShirt.setDescription("Blue with a corgi on a bike");
        TShirt.setPrice(300.0);
        TShirt.setQuantity(1);



        shopItemList.add(RunningShoes);
        shopItemList.add(Printer);
        shopItemList.add(CocaCola);
        shopItemList.add(Wokin);
        shopItemList.add(TShirt);

        return shopItemList;
    }
}
