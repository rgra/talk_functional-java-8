/*
 * @(#)Elections.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */

package de.rgra.functional.spliterators;

import java.util.Arrays;
import java.util.List;

import org.paumard.spliterators.MoreSpliterators;

import de.rgra.functional.spliterators.Elections.InvalidVote.Reason;

/**
 * @author rgra
 */
public class Elections {

	public static void main(String[] args) {
		List<VotingCard> votes = Arrays.asList(new VotingCard("x", null), new VotingCard(null, "x"), new VotingCard(null, null), new VotingCard("x", "I hate him"));
		int invalidVotes = MoreSpliterators.validate(votes.stream(), Elections::validate, Elections::validVote, Elections::invalidVote)
			.mapToInt(Vote::getInvalid)
			.sum();
		System.out.println(invalidVotes);
	}

	public static boolean validate(VotingCard card) {
		return card.getCandidate1Vote() != null ^ card.getCandidate2Vote() != null &&
				"x".equalsIgnoreCase(card.getCandidate1Vote()) ^ "x".equalsIgnoreCase(card.getCandidate2Vote());
	}

	public static Vote validVote(VotingCard card) {
		return new ValidVote("x".equalsIgnoreCase(card.getCandidate1Vote()), "x".equalsIgnoreCase(card.getCandidate2Vote()));
	}

	public static Vote invalidVote(VotingCard card) {
		final Reason reason;
		String vote1 = card.getCandidate1Vote();
		String vote2 = card.getCandidate2Vote();
		if (vote1 == null && vote2 == null) {
			reason = Reason.NO_VOTE;
		}
		else if (vote1 != null && vote2 != null) {
			reason = Reason.TWO_VOTES;
		}
		else {
			reason = Reason.SCIBBLING;
		}

		return new InvalidVote(reason);
	}

	static class VotingCard {
		private final String candidate1Vote;

		private final String candidate2Vote;

		public VotingCard(String candidate1Vote, String candidate2Vote) {
			this.candidate1Vote = candidate1Vote;
			this.candidate2Vote = candidate2Vote;
		}

		public String getCandidate1Vote() {
			return candidate1Vote;
		}

		public String getCandidate2Vote() {
			return candidate2Vote;
		}

		@Override
		public String toString() {
			return "VotingCard [candidate1Vote=" + candidate1Vote + ", candidate2Vote=" + candidate2Vote + "]";
		}

	}

	static interface Vote {
		default int getCandiate1() {
			return 0;
		}

		default int getCandidate2() {
			return 0;
		}

		default int getInvalid() {
			return 0;
		}
	}

	static class InvalidVote implements Vote {

		enum Reason {
			SCIBBLING,
			NO_VOTE,
			TWO_VOTES;
		}

		private final Reason reason;

		public InvalidVote(Reason reason) {
			this.reason = reason;
		}

		public Reason getReason() {
			return reason;
		}

		@Override
		public int getInvalid() {
			return 1;
		}

	}

	static class ValidVote implements Vote {
		private final boolean candidate1Vote;

		private final boolean candidate2Vote;

		public ValidVote(boolean candidate1Vote, boolean candidate2Vote) {
			this.candidate1Vote = candidate1Vote;
			this.candidate2Vote = candidate2Vote;
		}

		public boolean getCandidate1Vote() {
			return candidate1Vote;
		}

		public boolean getCandidate2Vote() {
			return candidate2Vote;
		}

		public int getCandiate1() {
			return candidate1Vote ? 1 : 0;
		}

		public int getCandidate2() {
			return candidate2Vote ? 1 : 0;
		}

	}

}
