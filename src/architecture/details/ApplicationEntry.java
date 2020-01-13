package architecture.details;

import io.javalin.Javalin;

public class ApplicationEntry {

	public final static int PORT = 8011;
	
	public static void main(String ... args) {
		SystemDetails details = new SystemDetails();
		
		Javalin app = Javalin.create().start(PORT);
		
		app.get("/info-processor", ctx -> ctx.result(details.generalInfoProcessor()));
		
		app.get("/info-disk", ctx -> ctx.result(details.getDiskStoreDetails()));
		
		app.get("/info-memory", ctx -> ctx.result(details.getMemoryDetails()));
		
		app.get("/info-networking", ctx -> ctx.result(details.getNetworkInfo()));
		
		app.get("/info-os", ctx -> ctx.result(details.getInfoOS()));
		
		app.get("/info-domain", ctx -> ctx.result(details.getDomainName()));
		
		app.get("info-processe", ctx -> ctx.result(details.getInfoProcesses()));
		
		app.get("/stop", ctx -> {new Thread(() -> app.stop()).start();});
	}
}
