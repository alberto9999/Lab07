package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	private UndirectedGraph<String,DefaultEdge>grafoParole=null;
    private HashSet<String>setVicini;
	
	
public HashSet <String> trovaTuttiVicini(String parola){
	setVicini= new HashSet<String>();
	recursive(setVicini,parola);	
	return setVicini;	
	
}
	
	private void  recursive(HashSet<String> setViciniParziale,String parola){
		for(String vertice: Graphs.neighborListOf(grafoParole, parola)){
			if(setViciniParziale.add(vertice)){
			recursive(setViciniParziale,vertice);
			}
		}
	}

	public List<String> createGraph(int numeroLettere) {
    WordDAO wDAO =new WordDAO();
    List<String> listaParoleLunghezza= new  ArrayList<String>(wDAO.getAllWordsFixedLength(numeroLettere));
    grafoParole= new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
   
    for(String parola: listaParoleLunghezza){
    	grafoParole.addVertex(parola);
    }
   
    generaArchi();
    
    return listaParoleLunghezza;
	}
	
	


	private void generaArchi() {
		for(String parola : grafoParole.vertexSet()){
			for(String altraParola : grafoParole.vertexSet()){
				if((parola!=altraParola)&&(!grafoParole.containsEdge(parola, altraParola))){ // i 2 vertici non hanno gia un arco in comune
					char[] parolaChar=parola.toCharArray();
					char[] altraParolaChar=altraParola.toCharArray();
					int cont =0;
					
					for(int i=0; i<parolaChar.length;i++){
						if(parolaChar[i]!=altraParolaChar[i]){
							cont++;
						}	
					}
					if(cont==1){
						grafoParole.addEdge(parola, altraParola);
					}	
				}
			}
		}
		
	}

	public List<String> displayNeighbours(String parolaInserita) {
	
		return Graphs.neighborListOf(grafoParole, parolaInserita);
	}
	
	
	

	public String findMaxDegree() {
		int maxDegree=0;
		String maxParola="";
		String result="";
	for(String s : grafoParole.vertexSet()){
		if(grafoParole.degreeOf(s)>maxDegree){
			maxDegree=grafoParole.degreeOf(s);
			maxParola=new String(s);
		}
	}
	result+="PAROLA CON GRADO MASSIMO: "+maxParola+"\nGRADO: "+maxDegree+"\nVICINI:\n";
	for(String str:Graphs.neighborListOf(grafoParole, maxParola) ){
		result+= str+"\n";
	}

		return result;
	}
}
