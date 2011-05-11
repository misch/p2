package ursuppe;

public class DivisionRateGene extends AbstractGene {
	
	public DivisionRateGene(Game game){
		super(game);
		super.price=6;
		super.mutationPoints=5;
	}

	@Override
	public void activate(Amoeba amoeba) {

	}
	@Override
	public void activateOnPlayer(Player player){
		super.activateOnPlayer(player);
		player.setDivisionCost(4);
	}

	@Override
	public void deactivate(Amoeba amoeba) {
		
	}
	public void deactivateOnPlayer(Player player){
		player.setDivisionCost(6);
		super.deactivateOnPlayer(player);
	}

}