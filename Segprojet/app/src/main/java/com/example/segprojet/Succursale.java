package com.example.segprojet;

import java.util.ArrayList;

public class Succursale {

    String name;
    ArrayList <String> Hours = new ArrayList<>();
    ArrayList<User> ListOfEmployees = new ArrayList <User>();
    ArrayList <Services> ListOfServices = new ArrayList<Services>();

    public Succursale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void AddEmployee(User user){
        ListOfEmployees.add(user);
    }
    public void AddService (Services service){
        ListOfServices.add(service);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getHours(String hours [][]) {

       return Hours;

    }

    public void setHours(ArrayList<String> hours) {


          for (int j=0;j<hours.size();j++) {
              Hours.add(j, hours.get(j));
          }
    }
}
