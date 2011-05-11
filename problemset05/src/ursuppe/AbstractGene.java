package ursuppe;

//DR nice!
public abstract class AbstractGene implements IGene{
	
	protected Player player;
	protected Game game;
	protected int price;
	protected int mutationPoints;
	
	public AbstractGene(Game game){
		this.game=game;
	}
	
	public abstract void deactivate(Amoeba amoeba);
	public abstract void activate(Amoeba amoeba);
	
	@Override
	public void activateOnPlayer(Player player){
		this.player=player;
	}
	public void deactivateOnPlayer(Player player){
		this.player=null;
	}

	public int getPrice(){
		return this.price;
	}
	
	public int getMutationPoints(){
		return mutationPoints;
	}
	

}
