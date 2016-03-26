package com.sdi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdi.model.Rating;

public class RatingsContainer {

	private Map<Long, List<Rating>> puntuaciones;


	public RatingsContainer() {
		puntuaciones = new HashMap<Long, List<Rating>>();
	}

	public void addRating(Long id, List<Rating> puntuaciones){
		this.puntuaciones.put(id, puntuaciones);
	}

	public String getAverageRatingValue(Long id){
		List<Rating> puntuaciones = this.puntuaciones.get(id);
		double average = 0.0;

		if(puntuaciones != null){
			for(Rating puntuacion : puntuaciones){
				average += puntuacion.getValue();
			}
			average = average/puntuaciones.size();
		}

		return !Double.isNaN(average) ? String.valueOf(average) : "No hay valoraciones sobre este usuario." ;
	}

	public List<String> getComments(Long id){
		List<String> comments = new ArrayList<String>();
		List<Rating> puntuaciones = this.puntuaciones.get(id);
		
		if(puntuaciones != null){
			for(Rating puntuacion: puntuaciones){
				comments.add(puntuacion.getComment());
			}
		}

		return comments;

	}
	
	public boolean isEnElViaje(Long id){
		return !puntuaciones.containsKey(id);
	}

}
