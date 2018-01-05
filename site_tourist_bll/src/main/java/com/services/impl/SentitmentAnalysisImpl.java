package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Commentaire;
import com.bo.Destination;
import com.bo.WordPolarity;
import com.dao.DestinationDao;
import com.dao.WordPolarityDao;
import com.services.SentimentAnalysis;

@Service
@Transactional
public class SentitmentAnalysisImpl implements SentimentAnalysis {

	@Autowired
	private WordPolarityDao wordPolDao;

	@Autowired
	private DestinationDao destinationDao;

	@Scheduled(fixedRate = 10000)
	public void destinationRating() {

		System.out.println("destinationRating...");

		List<Destination> destinations = destinationDao.getAll();

		double note = 0.0;

		for (Destination it : destinations) {

			System.out.println("dest. " + it);

			note = 0.0;

			// On récupère les commentaires de la destination
			List<Commentaire> comments = it.getCommentaires();

			// On clacule la polarité de chaque commentaire
			for (Commentaire itc : comments) {
				note += getTextPolarity(itc.getText());

			}

			if (comments.size() != 0) {
				it.setNote(note / comments.size());
			}

			destinationDao.update(it);

		}

	}

	public double getTextPolarity(String ptext) {

		// tockenize

		String[] tokens = ptext.split(" ");

		if (tokens.length == 0) {
			return 0;
		}

		double moy = 0.0;

		for (String it : tokens) {
			moy += getWordPolarity(it);
		}

		return moy / tokens.length;
	}

	private double getWordPolarity(String pWord) {

		List<WordPolarity> words = wordPolDao.getAll();

		for (WordPolarity it : words) {

			if (it.getWord().equals(pWord)) {
				return it.getPolarity();
			}
		}

		return 0;
	}

	public void addWord(WordPolarity word) {

		wordPolDao.create(word);

	}

}
