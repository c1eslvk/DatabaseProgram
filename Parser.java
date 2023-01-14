public class Parser {

    public void executeCommand(String commandStr, Database database) throws InvalidSyntaxException, TableNotFoundException{
        String[] command = commandStr.split(" ");
        if (command[0].equalsIgnoreCase("select")) {
            SelectParser selectParser = new SelectParser();
            selectParser.selectParse(command, database);
        } else if (command[0].equalsIgnoreCase("insert")) {
            InsertParser insertParser = new InsertParser();
            insertParser.insertParse(command, database);
        } else if (command[0].equalsIgnoreCase("update")) {
            UpdateParser updateParser = new UpdateParser();
            updateParser.updateParse(command, database);
        } else if (command[0].equalsIgnoreCase("delete")) {
            DeleteParser deleteParser = new DeleteParser();
            deleteParser.deleteParse(command, database);
        } else if (command[0].equalsIgnoreCase("create")) {
            CreateParser createParser = new CreateParser();
            createParser.createParse(command, database);
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
}