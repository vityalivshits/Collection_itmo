package Commands;

import Collection.FortressArrayList;
import UTILS.Message;
import UTILS.ParcelContainer;

public class HelpCommand implements Command {
    @Override
    public void serverRun(ParcelContainer container) {
        String s = "На данный момент времени Вам доступны следующие возможности:\n" +
                "quit   - Завершить работу.\n" +
                "info   - Общая информация о коллекции на текущий момент.\n" +
                "show   - Общая информация о каждом отдельном элементе коллекции.\n" +
                "clear  - Очистка коллекции.\n" +
                "add    - Добавление элемента в коллекцию.\n" +
                "remove - Удаление заданный элемент.\n" +
                "save   - Сохранение текущего состояния коллекции на сервере.\n" +
                "import - Добавление элементов из файла Клиента в коллекцию.\n" +
                "remove_lower - Удаление всех элементов, меньше заданного.\n";
        send(container, new Message(s));
    }
}
