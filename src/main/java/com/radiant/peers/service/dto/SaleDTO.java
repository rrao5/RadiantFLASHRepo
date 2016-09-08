package com.radiant.peers.service.dto;

public class SaleDTO {
	
	int NoCalls;
	int upsalecalls;
	int saleAmount;
	
	public int getNoCalls() {
		return NoCalls;
	}

	public void setNoCalls(int noCalls) {
		NoCalls = noCalls;
	}

	public int getUpsalecalls() {
		return upsalecalls;
	}

	public void setUpsalecalls(int upsalecalls) {
		this.upsalecalls = upsalecalls;
	}

	public int getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}

}
