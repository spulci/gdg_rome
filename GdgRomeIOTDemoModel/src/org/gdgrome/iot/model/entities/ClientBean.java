package org.gdgrome.iot.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ClientBean {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Key id;
		
		private String registrationId;
		private long timestamp = 0L;
		
		public ClientBean() {
			// TODO Auto-generated constructor stub
			registrationId = null;
		}
		
		public ClientBean(String registrationId){
			this.registrationId = registrationId;
		}
		
		public ClientBean(String registrationId, String messageId){
			this.registrationId = registrationId;
		}
		
		public Key getId() {
			return id;
		}

		public void setId(Key id) {
			this.id = id;
		}
		
		public String getRegistrationId() {
			return registrationId;
		}
		public void setRegistrationId(String registrationId) {
			this.registrationId = registrationId;
		}
		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}
		
		
		
		
		
		
}
