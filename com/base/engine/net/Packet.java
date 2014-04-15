/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

import java.io.Serializable;

/**
 *
 * @author Miguel_F
 */
public class Packet implements Serializable
{
	private Integer opcode;
	private Object data;

	public Packet(Integer id, Object dat)
	{
		setOpcode(id);
		setData(dat);
	}

	public void setData(Object data) { this.data = data; }
	public Object getData() { return data; }
	public void setOpcode(Integer opcode) { this.opcode = opcode; }
	public Integer getOpcode() { return opcode; }
}
