package dto;

public class MovieDTO {
	private String movienum;
	private String moviename;
	private String director;
	private String actor;
	private String first_run;
	private int rate;
	private String theater;
	private String theatertime;
	private String movieinfo;
	private int movieprice;
	private int ticketamount;
	private String userid;
	private String theaterlo;
	
	public String getTheaterlo() {
		return theaterlo;
	}

	public MovieDTO(String theaterlo) {
		super();
		this.theaterlo = theaterlo;
	}

	public void setTheaterlo(String theaterlo) {
		this.theaterlo = theaterlo;
	}

	/*
	public void setProdprice(int prodprice) {
		this.prodprice = prodprice;
	}
	*/
	public MovieDTO() {;}
	
	
	public MovieDTO(String movienum, String moviename, String director, String actor, String first_run, int rate,
			String theater, String theatertime, String movieinfo, int movieprice, int ticketamount, String userid,
			String theaterlo) {
		super();
		this.movienum = movienum;
		this.moviename = moviename;
		this.director = director;
		this.actor = actor;
		this.first_run = first_run;
		this.rate = rate;
		this.theater = theater;
		this.theatertime = theatertime;
		this.movieinfo = movieinfo;
		this.movieprice = movieprice;
		this.ticketamount = ticketamount;
		this.userid = userid;
		this.theaterlo = theaterlo;
	}

	public String getMovienum() {
		return movienum;
	}
	public String getMoviename() {
		return moviename;
	}
	public String getDirector() {
		return director;
	}
	public String getActor() {
		return actor;
	}
	public int getRate() {
		return rate;
	}
	public String getTheater() {
		return theater;
	}
	public String getTheatertime() {
		return theatertime;
	}
	public String getMovieinfo() {
		return movieinfo;
	}
	public int getMovieprice() {
		return movieprice;
	}
	public int getTicketamount() {
		return ticketamount;
	}
	public String getUserid() {
		return userid;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MovieDTO) {
			MovieDTO target = (MovieDTO)obj;
			if(target.movienum==this.movienum) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return movienum;
	}
}
