package com.services;

import java.util.List;

import com.bo.Destination;

public interface DestinationService {

	public void addDestination(Destination pDestination);

	public List<Destination> getAllDestinations();

	public void updateDestination(Destination destination);

	public Destination getDestinationById(Long id);

}
