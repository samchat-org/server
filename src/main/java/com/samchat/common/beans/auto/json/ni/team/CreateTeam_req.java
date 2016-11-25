package com.samchat.common.beans.auto.json.ni.team;

public class CreateTeam_req{

	private String tname       ;
	private String owner       ;
	private String members     ;
	private String announcement;
	private String intro       ;
	private String msg         ;
	private String magree      ;
	private String joinmode    ;
	private String custom      ;
	private String icon        ;
	private String beinvitemode;
	private String invitemode  ;
	private String uptinfomode ;
	private String upcustommode;

	public String getTname       () {
		return tname       ;
	}

	public void setTname       (String tname       ) {
		this.tname        = (tname        == null? null : tname       .trim());
	}

	public String getOwner       () {
		return owner       ;
	}

	public void setOwner       (String owner       ) {
		this.owner        = (owner        == null? null : owner       .trim());
	}

	public String getMembers     () {
		return members     ;
	}

	public void setMembers     (String members     ) {
		this.members      = (members      == null? null : members     .trim());
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = (announcement == null? null : announcement.trim());
	}

	public String getIntro       () {
		return intro       ;
	}

	public void setIntro       (String intro       ) {
		this.intro        = (intro        == null? null : intro       .trim());
	}

	public String getMsg         () {
		return msg         ;
	}

	public void setMsg         (String msg         ) {
		this.msg          = (msg          == null? null : msg         .trim());
	}

	public String getMagree      () {
		return magree      ;
	}

	public void setMagree      (String magree      ) {
		this.magree       = (magree       == null? null : magree      .trim());
	}

	public String getJoinmode    () {
		return joinmode    ;
	}

	public void setJoinmode    (String joinmode    ) {
		this.joinmode     = (joinmode     == null? null : joinmode    .trim());
	}

	public String getCustom      () {
		return custom      ;
	}

	public void setCustom      (String custom      ) {
		this.custom       = (custom       == null? null : custom      .trim());
	}

	public String getIcon        () {
		return icon        ;
	}

	public void setIcon        (String icon        ) {
		this.icon         = (icon         == null? null : icon        .trim());
	}

	public String getBeinvitemode() {
		return beinvitemode;
	}

	public void setBeinvitemode(String beinvitemode) {
		this.beinvitemode = (beinvitemode == null? null : beinvitemode.trim());
	}

	public String getInvitemode  () {
		return invitemode  ;
	}

	public void setInvitemode  (String invitemode  ) {
		this.invitemode   = (invitemode   == null? null : invitemode  .trim());
	}

	public String getUptinfomode () {
		return uptinfomode ;
	}

	public void setUptinfomode (String uptinfomode ) {
		this.uptinfomode  = (uptinfomode  == null? null : uptinfomode .trim());
	}

	public String getUpcustommode() {
		return upcustommode;
	}

	public void setUpcustommode(String upcustommode) {
		this.upcustommode = (upcustommode == null? null : upcustommode.trim());
	}

}