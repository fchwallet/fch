package com.xyz.fch_sp.app.modular.api.dto;

public class TxInputDto {

	private String txid;

	private Integer vout;

	private String scriptPubKey;

	public TxInputDto(String txid, Integer vout, String scriptPubKey) {

		this.txid = txid;

		this.vout = vout;

		this.scriptPubKey = scriptPubKey;
	}


	public String txid() {
		return txid;
	}

	public int vout() {
		return vout;
	}

	public String scriptPubKey() {
		return scriptPubKey;
	}

}
