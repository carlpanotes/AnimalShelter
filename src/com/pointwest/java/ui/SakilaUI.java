package com.pointwest.java.ui;

import java.util.List;

import com.pointwest.java.bean.FilmBean;
import com.pointwest.java.manager.SakilaManager;

public class SakilaUI {

	public void getFilms() {
		// TODO Auto-generated method stub
		SakilaManager manager = new SakilaManager();
		//manager.getFilm();
		
		List<FilmBean> film = manager.getFilm();
		
		for (FilmBean filmList : film) {
			System.out.println(filmList.getTitle());
		}
		//System.out.println("carl");
	}
}
