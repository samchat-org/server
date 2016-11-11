package com.samchat.common.beans.auto.json.appserver.profile;

import java.util.ArrayList;
public class GoogleplaceAutocomplete_res{

	private ArrayList<Predictions> predictions;
	private String status = "";

	public static class Matched_substrings {
		private long length;
		private long offset;

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public long getOffset() {
			return offset;
		}

		public void setOffset(long offset) {
			this.offset = offset;
		}
	}

	public static class Structured_formatting {
		private String main_text = "";
		private ArrayList<Main_text_matched_substrings> main_text_matched_substrings;
		private String secondary_text = "";
		private ArrayList<Secondary_text_matched_substrings> secondary_text_matched_substrings;

		public String getMain_text() {
			return main_text;
		}

		public void setMain_text(String main_text) {
			this.main_text = (main_text == null? "" : main_text.trim());
		}

		public ArrayList<Main_text_matched_substrings> getMain_text_matched_substrings() {
			return main_text_matched_substrings;
		}

		public void setMain_text_matched_substrings(ArrayList<Main_text_matched_substrings> main_text_matched_substrings) {
			this.main_text_matched_substrings = main_text_matched_substrings;
		}

		public String getSecondary_text() {
			return secondary_text;
		}

		public void setSecondary_text(String secondary_text) {
			this.secondary_text = (secondary_text == null? "" : secondary_text.trim());
		}

		public ArrayList<Secondary_text_matched_substrings> getSecondary_text_matched_substrings() {
			return secondary_text_matched_substrings;
		}

		public void setSecondary_text_matched_substrings(ArrayList<Secondary_text_matched_substrings> secondary_text_matched_substrings) {
			this.secondary_text_matched_substrings = secondary_text_matched_substrings;
		}
	}

	public static class Main_text_matched_substrings {
		private long length;
		private long offset;

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public long getOffset() {
			return offset;
		}

		public void setOffset(long offset) {
			this.offset = offset;
		}
	}

	public static class Secondary_text_matched_substrings {
		private long length;
		private long offset;

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public long getOffset() {
			return offset;
		}

		public void setOffset(long offset) {
			this.offset = offset;
		}
	}

	public static class Terms {
		private long offset;
		private String value = "";

		public long getOffset() {
			return offset;
		}

		public void setOffset(long offset) {
			this.offset = offset;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = (value == null? "" : value.trim());
		}
	}

	public static class Predictions {
		private String description = "";
		private String id = "";
		private ArrayList<Matched_substrings> matched_substrings;
		private String place_id = "";
		private String reference = "";
		private Structured_formatting structured_formatting;
		private ArrayList<Terms> terms;
		private ArrayList<String> types;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = (description == null? "" : description.trim());
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = (id == null? "" : id.trim());
		}

		public ArrayList<Matched_substrings> getMatched_substrings() {
			return matched_substrings;
		}

		public void setMatched_substrings(ArrayList<Matched_substrings> matched_substrings) {
			this.matched_substrings = matched_substrings;
		}

		public String getPlace_id() {
			return place_id;
		}

		public void setPlace_id(String place_id) {
			this.place_id = (place_id == null? "" : place_id.trim());
		}

		public String getReference() {
			return reference;
		}

		public void setReference(String reference) {
			this.reference = (reference == null? "" : reference.trim());
		}

		public Structured_formatting getStructured_formatting() {
			return structured_formatting;
		}

		public void setStructured_formatting(Structured_formatting structured_formatting) {
			this.structured_formatting = structured_formatting;
		}

		public ArrayList<Terms> getTerms() {
			return terms;
		}

		public void setTerms(ArrayList<Terms> terms) {
			this.terms = terms;
		}

		public ArrayList<String> getTypes() {
			return types;
		}

		public void setTypes(ArrayList<String> types) {
			this.types = types;
		}
	}

	public ArrayList<Predictions> getPredictions() {
		return predictions;
	}

	public void setPredictions(ArrayList<Predictions> predictions) {
		this.predictions = predictions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = (status == null? "" : status.trim());
	}

}