package org.example;
import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.*;

public class Main {
    public static void main(String[] args) {

        // create default schema
        DbSpec spec = new DbSpec();
        DbSchema schema = spec.addDefaultSchema();

        // add table with basic customer info
        DbTable customerTable = schema.addTable("customer");
        DbColumn custIdCol = customerTable.addColumn("cust_id", "number", null);
        DbColumn custNameCol = customerTable.addColumn("name", "varchar", 255);

        // add order table with basic order info
        DbTable orderTable = schema.addTable("order");
        DbColumn orderIdCol = orderTable.addColumn("order_id", "number", null);
        DbColumn orderCustIdCol = orderTable.addColumn("cust_id", "number", null);
        DbColumn orderTotalCol = orderTable.addColumn("total", "number", null);
        DbColumn orderDateCol = orderTable.addColumn("order_date", "timestamp", null);

        // add a join from the customer table to the order table (on cust_id)
        DbJoin custOrderJoin = spec.addJoin(null, "customer",
                null, "order",
                "cust_id");

        String createCustomerTable =
                new CreateTableQuery(customerTable, true)
                        .validate().toString();
        System.out.println(createCustomerTable);

        // => CREATE TABLE customer (cust_id number,name varchar(255))

        String createOrderTable =
                new CreateTableQuery(orderTable, true)
                        .validate().toString();
        System.out.println(createOrderTable);

        // => CREATE TABLE order (order_id number,cust_id number,total number,order_date timestamp)
    }
}