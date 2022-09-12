package org.example;
import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.*;
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        // CODE TO PARSE JSON FILE
        JSONParser parser = new JSONParser();
        String InputFilePath = "input.json";
        JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(InputFilePath));
        List roles = (List) jsonObject.get("ROLES");
        for(Object role:roles){
            System.out.println(role.toString());
        }

        List forms = (List) jsonObject.get("FORMS");
        for(Object form:forms){
            System.out.println(form.toString());
        }

        //CODE TO WRITE TO TEXT FILE




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