package com.pointwest.java.manager;

import java.util.List;

import com.pointwest.java.bean.FilmBean;
import com.pointwest.java.dao.FilmDao;

public class SakilaManager {
	public List<FilmBean> getFilm(){
		FilmDao filmdao = new FilmDao();
		//List<FilmBean> films = filmdao.getFilms();
		//List<FilmBean> films = filmdao.getSpecificFilm();
		List<FilmBean> films = filmdao.searchFilm();
		return films;
		
	}
}
