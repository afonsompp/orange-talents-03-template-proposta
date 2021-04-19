package br.com.xyzbank.proposal.managecard;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

public class NotifyTravelRequest {

	@NotBlank
	private String destination;
	@FutureOrPresent
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate date;

	@Deprecated
	public NotifyTravelRequest() {

	}

	public NotifyTravelRequest(String destination, LocalDate date) {
		this.destination = destination;
		this.date = date;
	}

	public String getDestination() {
		return this.destination;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public NotifyTravel toNotifyTravel(String ip, String userAgent) {
		return new NotifyTravel(destination, date, ip, userAgent);
	}
}
