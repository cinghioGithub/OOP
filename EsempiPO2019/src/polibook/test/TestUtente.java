package polibook.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import polibook.Messaggio;
import polibook.Utente;

public class TestUtente {

	@Test
	public void test() {
		Utente mark = new Utente("mark","Zucc","Mark","mark@polito.it");
		
		assertEquals("mark",mark.getNickname());
	}
	
	@Test
	public void testAmicizia() {
		Utente mark = new Utente("mark","Zucc","Mark","mark@polito.it");
		
		Utente jane = new Utente("jane","Doe","Jane","jane@polito.it");
		
		mark.nuovoAmico(jane);
		
		Collection<Utente> amiciMark = mark.getAmici();
		
		assertNotNull("Manca la collezione degli amici",amiciMark);
		assertTrue(amiciMark.contains(jane));
		
		Collection<Utente> amiciJane = jane.getAmici();
		
		assertTrue(amiciJane.contains(mark));
		
	}

	@Test
	public void testMesaggio() {
		Utente mark = new Utente("mark","Zucc","Mark","mark@polito.it");
		
		final String testo = "Buona Pasqua in anticipo...";
		mark.scriviMesaggio(testo);
		
		Collection<Messaggio> messaggi = mark.getMessaggi();
		
		assertNotNull(messaggi);
		
		assertEquals(1, messaggi.size());
		
		Messaggio msg = messaggi.iterator().next();
		
		assertEquals(testo,msg.getTesto());

	}
	
	@Test
	public void testMessaggiAmici() {
		Utente mark = new Utente("mark","Zucc","Mark","mark@polito.it");
		
		Utente jane = new Utente("jane","Doe","Jane","jane@polito.it");
		
		Utente bill = new Utente("bill","Gates","Bill","bill@polito.it");
		
		mark.nuovoAmico(jane);
		mark.nuovoAmico(bill);
		
		jane.scriviMesaggio("Primo messaggio!");
		
		jane.scriviMesaggio("Ciao a tutti!!!");
		bill.scriviMesaggio("Hello...");
		jane.scriviMesaggio("C'è nesssuno??");
		bill.scriviMesaggio("...world");
		
		List<Messaggio> messaggi = mark.getMessagiAmici();
		
		assertEquals(5, messaggi.size());
		
		assertEquals("Hello...", messaggi.get(2).getTesto());
		assertEquals("...world", messaggi.get(0).getTesto());
		
		
	}

}
