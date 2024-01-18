# Gra w Go

Projekt jest w fazie rozwoju.

## Opis

Gra w Go to implementacja popularnej gry planszowej Go. Projekt rozwijany jest w celu dostarczenia platformy do gry dla użytkowników.

Można zagrać z przeciwnikiem bądź z botem lub odtworzyć jedną z wcześniejszych gier.

Gra toczy się na planszy będącej siatką 19 poziomych i 19 pionowych linii tworzących 361 przecięć.

Gracze kładą na przemian czarne i białe kamienie na przecięciu linii. Rozpoczynają czarne.

Plansza jest początkowo pusta. Celem gry jest otoczenie własnymi kamieniami obszaru większego niż obszar przeciwnika.

Kamieni raz postawionych na planszy nie można zabrać ani przesunąć, mogą natomiast zosta¢ uduszone przez przeciwnika, jeżli stracą wszystkie oddechy. Po zabraniu ostatniego oddechu przeciwnik zabiera z planszy i przechowuje uduszone przez siebie kamienie (zwane jeńcami). Oddechem kamienia nazywamy niezajęte sąsiednie przecięcie (połączone linią z kamieniem). Na przykład kamień stojący samotnie na środku planszy ma cztery oddechy, a w rogu planszy - dwa. Kamienie jednego koloru stojące obok siebie i połączone liniami tworzą łańcuch, który ma wspólne oddechy można je zbić albo wszystkie razem albo żadnego.
Gracz nie może pozbawić swojej grupy kamieni ostatniego oddechu, ani położyć kamienia w punkt bez oddechu. Wyjątkiem od reguły jest sytuacja, w której taki ruch dusi kamienie przeciwnika.
Kształt, w którym gracze mogą naprzemiennie dusić kamień przeciwnika, zwany jest ko. W celu uniknięcia nieskończonych cykli gracz, którego kamień został uduszony w ko, nie może udusić kamienia przeciwnika w następnym ruchu.

Gracz zawsze może zrezygnować z ruchu. Gdy obaj gracze bezpośrednio po sobie zrezygnują z ruchu, gra się zatrzymuje i wyświetlane są informacje dotyczące zajmowanego terenu i jeńców. Póżniej można zakończyć grę lub grać dalej.
Jeśli gracze zdecydują, że gra się skończyła, usuwane są kamienie znajdujące się na terytorium przeciwnika i dodawane są do jeńców. Następnie jeńcy ustawiani są na terytorium przeciwnika, po czym podliczane i porównywane są punkty terytorium. Gracz z większym terytorium wygrywa.

## Instrukcje

### Instalacja

Aby zainstalować projekt, wykonaj następujące kroki:

1. Sklonuj repozytorium: `git clone [adres_repozytorium]`
2. Przejdź do katalogu projektu: `cd gra_w_go`
3. Uruchom serwer: `mvn exec:java -Dexec.mainClass=com.go.Serwer`
4. Uruchom dwukrotnie grę: `mvn exec:java -Dexec.mainClass=com.go.GUI.Aplikacja` i wybierz opcję (gracz/bot/odtworzenie gry)

### Użycie

Po uruchomieniu projektu, gra w Go będzie dostępna poprzez interfejs użytkownika. 

Będą dostępne 3 opcje:
- gra a innym graczen
- gra z botem
- odtworzenie jednej w wcześniejszych gier

Po kliknieciu przycisku "Gra z innym graczem", wyświetla się plansza. Jeśli nie ma drugiego gracza czeka się na niego.
Po kliknieciu przycisku "Gra z botem", wyświetla się plansza.
Po kliknieciu przycisku "Odtworzenie gry", wybiera się którą grę chce się odtworzyć po czym wyświetlana jest plansza na której wyświetlana jest stara gra.

## Autorzy

- Anna Świerzy
- Ryszard Chereźniak
