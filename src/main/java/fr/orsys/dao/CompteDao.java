package fr.orsys.dao;

import java.util.List;

import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;

public interface CompteDao extends Dao<Compte, Integer> {
	public List<CompteEpargne> loadCompteEpargne();

}
