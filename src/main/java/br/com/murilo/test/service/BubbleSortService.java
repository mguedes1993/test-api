package br.com.murilo.test.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class BubbleSortService {
	public int[] sort(int[] values){
		var localValues = Arrays.copyOf(values, values.length);
		
		while(process(localValues));
		
		return localValues;
	}
	
	private boolean process(int[] values) {
		var changed = false;
		for (int i = 0; i < values.length-1; i++) {
			var actual = values[i];
			var next = values[i+1];
			if(actual > next) {
				values[i] = next;
				values[i+1] = actual;
				changed = true;
			}
		}
		
		return changed;
	}
}
