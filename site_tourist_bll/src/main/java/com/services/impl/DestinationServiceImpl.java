package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Destination;
import com.dao.DestinationDao;
import com.services.DestinationService;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationDao destinationDao;

	@Override
	public void addDestination(Destination pDestination) {

		destinationDao.create(pDestination);

	}

	public List<Destination> getAllDestinations() {
		return destinationDao.getAll();
	}

	public void updateDestination(Destination destination) {

		destinationDao.update(destination);
	}

	public Destination getDestinationById(Long id) {
		return destinationDao.findById(id);
	}

}
