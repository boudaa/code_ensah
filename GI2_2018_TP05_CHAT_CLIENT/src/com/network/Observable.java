package com.network;

import com.ensah.chat.client.gui.Observer;

public interface Observable {

	public void notifyAllObservers(Object pObject);

	public void addObserver(Observer pObserver);

}
