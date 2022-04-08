package com.example.app_formarmor;

public class Client {

        private int id;
        private String nom;
        //private String statut;
        private String num_tel;

        public Client()
        {
        }

        public Client(int id, String nom, String num_tel)
        {
            this.id = id;
            this.nom = nom;
            //this.statut = statut;
            this.num_tel = num_tel;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public void setNom(String nom)
        {
            this.nom = nom;
        }

    /*public void setStatut(String statut)
    {
        this.statut = statut;
    }*/

        public void setNum_tel(String num_tel)
        {
            this.num_tel = num_tel;
        }

        public int getId()
        {
            return id;
        }

        public String getNom()
        {
            return nom;
        }

    /*public String getStatut()
    {
        return statut;
    }*/

        public String getNum_tel()
        {
            return num_tel;
        }

    }
