package org.example;
import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.*;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.*;
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        // CODE TO PARSE JSON FILE
        JSONParser parser = new JSONParser();
        String InputFilePath = "input.json";
        JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(InputFilePath));
        System.out.println(jsonObject.toString());
        List<JSONObject> roles = (List<JSONObject>) jsonObject.get("Roles");
         for(JSONObject role:roles){
             System.out.println(role.toString());
         }

        List<JSONObject> forms = (List<JSONObject>) jsonObject.get("Forms");
        for(Object form:forms){
            System.out.println(form.toString());
        }

        //CODE TO WRITE TO TEXT FILE
        String OutputFileName = "sqlScript.txt";
        PrintWriter out = new PrintWriter(OutputFileName);
        //out used below


        // create default schema
        DbSpec spec = new DbSpec();
        DbSchema schema = spec.addDefaultSchema();

        // ROLES TABLE
        // CREATE
        DbTable Roles = schema.addTable("Roles");
        DbColumn role_id = Roles.addColumn("role_id", "number", null);
        DbColumn role_name = Roles.addColumn("role_name", "varchar", 255);
        String createRolesTable =
                new CreateTableQuery(Roles, true)
                        .validate().toString();
        System.out.println(createRolesTable);
        // POPULATE
        for(JSONObject role: roles)
        {
                String insertCustomerQuery =
                new InsertQuery(Roles)
                .addColumn(role_id, role.get("id"))
                .addColumn(role_name, role.get("name"))
                .validate().toString();

                System.out.println(insertCustomerQuery);
        }


        // USER TABLE
        // CREATE
//         DbTable users = schema.addTable("Users");
//         DbColumn user_id = Roles.addColumn("user_id", "number", null);
//         DbColumn user_name = Roles.addColumn("user_name", "varchar", 255);
//         DbColumn role_id_users = Roles.addColumn("role_id", "number", null);
//         DbColumn user_email = Roles.addColumn("user_email", "varchar", 255);
//         String createUsersTable =
//                 new CreateTableQuery(users, true)
//                         .validate().toString();
//         System.out.println(createUsersTable);


//         // FORM TABLE
//         // CREATE
//         DbTable forms = schema.addTable("Forms");
//         DbColumn form_id = forms.addColumn("form_id", "number", null);
//         DbColumn form_name = forms.addColumn("form_name", "varchar", 255);
//         String createFormsTable =
//                 new CreateTableQuery(forms, true)
//                         .validate().toString();
//         System.out.println(createFormsTable);

















//         // add table with basic customer info
//         DbTable customerTable = schema.addTable("customer");
//         DbColumn custIdCol = customerTable.addColumn("cust_id", "number", null);
//         DbColumn custNameCol = customerTable.addColumn("name", "varchar", 255);

//         // add order table with basic order info
//         DbTable orderTable = schema.addTable("order");
//         DbColumn orderIdCol = orderTable.addColumn("order_id", "number", null);
//         DbColumn orderCustIdCol = orderTable.addColumn("cust_id", "number", null);
//         DbColumn orderTotalCol = orderTable.addColumn("total", "number", null);
//         DbColumn orderDateCol = orderTable.addColumn("order_date", "timestamp", null);

//         // add a join from the customer table to the order table (on cust_id)
//         DbJoin custOrderJoin = spec.addJoin(null, "customer",
//                 null, "order",
//                 "cust_id");

//         String createCustomerTable =
//                 new CreateTableQuery(customerTable, true)
//                         .validate().toString();

//         System.out.println(createCustomerTable);
//         //write to output file
//         out.println(createCustomerTable);

//         // => CREATE TABLE customer (cust_id number,name varchar(255))

//         String createOrderTable =
//                 new CreateTableQuery(orderTable, true)
//                         .validate().toString();
//         System.out.println(createOrderTable);
//         //write to output file
//         out.println(createOrderTable);
//         out.close();
//         // => CREATE TABLE order (order_id number,cust_id number,total number,order_date timestamp)
//     }
}
}
// class Role{
//         public int role_id;
//         public String role_name;

//         Role(int role_id, String role_name)
//         {
//                 this.role_id = role_id;
//                 this.role_name = role_name;
//         }
// }

// class FormField{
//         public int field_id;
//         public String field_name;
//         public String field_type;

//         FormField(int field_id, String field_name, String field_type)
//         {
//                 this.field_id = field_id;
//                 this.field_name = field_name;
//                 this.field_type = field_type;
//         }
// }
// class Form{
//         public int form_id;
//         public String form_name;
//         public FormField
// }