package com.example.practica_leer_json;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonController {
    public static final ObjectMapper JSON_MAPPER=new ObjectMapper();

    @FXML
    private TextField texto_titulo;

    @FXML
    private TextField texto_fecha;

    @FXML
    private TextField texto_genero;

    @FXML
    private TextField texto_director;

    @FXML
    private ListView<String> listView_pelicula;
    ArrayList<String>lista_titulos=new ArrayList<String>();

    @FXML
    private Button botton_importar;

    //variables onde vou armazenar o que tenho no archivo json
    String variable_titu=" ";
    String variable_data=" ";
    String variable_direc=" ";
    String variable_gene=" ";

    HashMap<String,String>mapa_datas=new HashMap<String,String>();
    HashMap<String,String>mapa_directores=new HashMap<String,String>();
    HashMap<String,String>mapa_generos=new HashMap<String,String>();

    @FXML
    void Importar(ActionEvent event) {
        try{
            ArrayList<String>listatitulospeliculas=new ArrayList<String>();//arrayList onde quero armazenar os titulos, logo poner dentro do listview
            ArrayList<Peliculas> pelis = JSON_MAPPER.readValue(new File("src/main/resources/Peliculas.json"),
                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Peliculas.class));


            for(int i=0;i< pelis.size();i++){//recorre los datos que tenemos en Json
                //cada variable recebe o que tenho no archivo json
                variable_titu=pelis.get(i).getTitulo();
                variable_data=pelis.get(i).getFecha();
                variable_direc=pelis.get(i).getDirector();
                variable_gene=pelis.get(i).getGenero();

                //dentro de cada haspmap tenho o titulo(clave) e a variable que correponda
                mapa_datas.put(variable_titu,variable_data);
                mapa_directores.put(variable_titu,variable_direc);
                mapa_generos.put(variable_titu,variable_gene);

                //añadir no arraylist cada titulo
                listatitulospeliculas.add(pelis.get(i).getTitulo());

                /*Peliculas p=new Peliculas();
                p=(Peliculas) pelis.get(i);*/
            }
           //poner dentro do listView o que tenho no arraylist (listatitulospeliculas)
            listView_pelicula.getItems().addAll(listatitulospeliculas);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void selecionarPeliculas(Event event) {
        try {
            //cuando eu selecionar um titulo que tenho no listview, cada  campo do ScebeBuider tendra um valor
            texto_titulo.setText(listView_pelicula.getSelectionModel().getSelectedItem());
            texto_fecha.setText(mapa_datas.get(listView_pelicula.getSelectionModel().getSelectedItem()));
            texto_director.setText(mapa_directores.get(listView_pelicula.getSelectionModel().getSelectedItem()));
            texto_genero.setText(mapa_generos.get(listView_pelicula.getSelectionModel().getSelectedItem()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
