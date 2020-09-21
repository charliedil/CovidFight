package com.example.covidfight;

import java.util.ArrayList;

public class VcuCase{

        ArrayList< Data > positives = new ArrayList < Data > ();
        ArrayList < Data > negatives = new ArrayList < Data > ();
        ArrayList < Data > isolations = new ArrayList < Data > ();
        ArrayList < Data > quarantines = new ArrayList < Data > ();
        ArrayList < Data > students = new ArrayList < Data> ();
        ArrayList < Data > employees = new ArrayList < Data > ();
        ArrayList < Data > prevalenceNegative = new ArrayList < Data > ();
        ArrayList < Data > prevalencePositive = new ArrayList < Data > ();
        ArrayList < Data > totalStudents = new ArrayList < Data> ();
        ArrayList < Data > totalEmployees = new ArrayList < Data > ();


        // Getter Methods

        public ArrayList<Data> getPositives() {
            return positives;
        }

        public void setPositives(ArrayList<Data> positives) {
            this.positives = positives;
        }

        public ArrayList<Data> getNegatives() {
            return negatives;
        }

        public void setNegatives(ArrayList<Data> negatives) {
            this.negatives = negatives;
        }

        public ArrayList<Data> getIsolations() {
            return isolations;
        }

        public void setIsolations(ArrayList<Data> isolations) {
            this.isolations = isolations;
        }

        public ArrayList<Data> getQuarantines() {
            return quarantines;
        }

        public void setQuarantines(ArrayList<Data> quarantines) {
            this.quarantines = quarantines;
        }

        public ArrayList<Data> getStudents() {
            return students;
        }

        public void setStudents(ArrayList<Data> students) {
            this.students = students;
        }

        public ArrayList<Data> getEmployees() {
            return employees;
        }

        public void setEmployees(ArrayList<Data> employees) {
            this.employees = employees;
        }

        public ArrayList<Data> getPrevalenceNegative() {
            return prevalenceNegative;
        }

        public void setPrevalenceNegative(ArrayList<Data> prevalenceNegative) {
            this.prevalenceNegative = prevalenceNegative;
        }

        public ArrayList<Data> getPrevalencePositive() {
            return prevalencePositive;
        }

        public void setPrevalencePositive(ArrayList<Data> prevalencePositive) {
            this.prevalencePositive = prevalencePositive;
        }

        public ArrayList<Data> getTotalStudents() {
            return totalStudents;
        }

        public void setTotalStudents(ArrayList<Data> totalStudents) {
            this.totalStudents = totalStudents;
        }

        public ArrayList<Data> getTotalEmployees() {
            return totalEmployees;
        }

        public void setTotalEmployees(ArrayList<Data> totalEmployees) {
            this.totalEmployees = totalEmployees;
        }


        // Setter Methods


    }

