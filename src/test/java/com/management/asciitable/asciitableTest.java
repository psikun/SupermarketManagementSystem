package com.management.asciitable;

import com.management.domain.Goods;
import de.vandermeer.asciitable.AsciiTable;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author SiKun
 * @date 2021/05/19/ 10:33
 */

public class asciitableTest {
    @Test
    public void printTable() {
        AsciiTable at = new AsciiTable();

        Goods goods = new Goods();
        goods.setName("苹果");
        goods.setPrice(5000);
        goods.setSales(50);
        at.addRule();
        at.addRow("商品名称", "分类", "价格");
        at.addRule();
        at.addRow(goods.getName(), goods.getPrice(), goods.getSales());
        at.addRule();
        at.getContext().setWidth(40);
        String render = at.render();
        System.out.println(render);
    }
}