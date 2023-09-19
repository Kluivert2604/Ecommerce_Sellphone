package com.kieudatquochung.ecommercesellphone.Helper;

import android.content.Context;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.Domain.PopularDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertProduct(PopularDomain item){
        ArrayList<PopularDomain> listPop = getListCart();
        boolean exitAlready = false;
        int n = 0;
        for (int i = 0; i < listPop.size(); i++) {
            if (listPop.get(i).getTitle().equals(item.getTitle())){
                exitAlready = true;
                n = i;
                break;
            }
        }
        if (exitAlready){
            listPop.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listPop.add(item);
        }
        tinyDB.putListObject("CartList", listPop);
        Toast.makeText(context, "Đã thêm vào giỏ hàng của bạn", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void minusNumberItem(ArrayList<PopularDomain>listItem, int position, ChangNumberItemListener changNumberItemListener){
        if (listItem.get(position).getNumberInCart() == 1){
            listItem.remove(position);
        } else {
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", listItem);
        changNumberItemListener.change();
    }
    public void plusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangNumberItemListener changNumberItemListener){
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", listItem);
        changNumberItemListener.change();
    }
    public Double getTotalFee(){
        ArrayList<PopularDomain> listItem = getListCart();
        double fee = 0;
        for (int i = 0; i < listItem.size(); i++) {
            fee = fee + (listItem.get(i).getPrice() * listItem.get(i).getNumberInCart());

        }
        return fee;
    }
}
