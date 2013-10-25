package ronda.engine.elements;

import java.util.List;

public class Player {
	String			identifier;
	List<Card>		wonCardsPerHeap;
	List<Card>		handCardsPerRound;
	List<Card>		initialHandCardsPerRound;
	Announcement	announcementPerRound;
	
	public Player(String id) {
		assert(id != null);
		
		identifier = id;
		announcementPerRound = Announcement.nothingAnnounced;
	}

	public List<Card> getWonCardsPerHeap() {
		return wonCardsPerHeap;
	}

	public void setWonCardsPerHeap(List<Card> wonCardsPerHeap) {
		this.wonCardsPerHeap = wonCardsPerHeap;
	}

	public List<Card> getHandCardsPerRound() {
		return handCardsPerRound;
	}

	public void setHandCardsPerRound(List<Card> handCardsPerRound) {
		this.handCardsPerRound = handCardsPerRound;
	}

	public List<Card> getInitialHandCardsPerRound() {
		return initialHandCardsPerRound;
	}

	public void setInitialHandCardsPerRound(List<Card> initialHandCardsPerRound) {
		this.initialHandCardsPerRound = initialHandCardsPerRound;
	}

	public Announcement getAnnouncementPerRound() {
		return announcementPerRound;
	}

	public void setAnnouncementPerRound(Announcement announcementPerRound) {
		this.announcementPerRound = announcementPerRound;
	}

	public String getIdentifier() {
		return identifier;
	}
}
