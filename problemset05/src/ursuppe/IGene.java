package ursuppe;

public interface IGene {
	
	public void activate(Amoeba amoeba);
	
	public void deactivate(Amoeba amoeba);
	
	public int getPrice();

	public void activateOnPlayer(Player player);
	
	public int getMutationPoints();

}
