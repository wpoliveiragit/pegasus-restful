package br.com.projeto.crud.infra.statics.logger;

public class Main {

	public static void main(String[] args) {
		SmartColorLogger log = new SmartColorLogger(new Object() {
		}.getClass().getEnclosingClass());
		log.info("Mensagem de INFO");
		log.warn("Mensagem de WARNING");
		log.error("Mensagem de ERROR");
	}

}
