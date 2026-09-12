
# 🧇 Poisoned Waffle

**Poisoned Waffle** is a strategic Java game where two players compete over a waffle… with a deadly twist 🧪

---

## 🎮 Game Concept

The game is played on a grid (the “waffle”).
Each cell can be eaten… except one 👇

👉 **The poisoned cell**
➡️ The player who eats it **loses instantly**

---

## 🧠 Rules

* Players take turns
* On each turn, a player selects a cell
* All cells **to the bottom-right** of the selected cell are “eaten”
* The player forced to eat the poisoned cell loses
---

## 👥 Game Modes

### 🤝 Human vs Human

Two players play on the same machine

### 🤖 Human vs AI

Play against the computer with different difficulty levels:

* 🟢 **Easy**: random moves
* 🟡 **Medium**: basic strategy
* 🔴 **Hard**: optimized AI (plays smart)

---

## 🚀 Run the Project

### 1. Compile

```bash
./compile.sh
```

### 2. Run

```bash
./run.sh
```

---

## 📦 Build a `.jar`

```bash
jar cfe app.jar main.Main -C bin . -C resources .
```

Then run:

```bash
java -jar app.jar
```

---

## 📁 Project Structure

```text
.
├── main/          # entry point
├── view/          # graphical interface
├── controller/    # input handling
├── modele/        # game logic
├── resources/     # images
├── bin/           # compiled files
```

---

## 🖼️ Assets

Game images (waffle, poison, etc.) are located in:

```text
resources/images/
```

---

## 🛠️ Technologies

* Java
* Swing (GUI)

---

## ✨ Possible Improvements

* Online multiplayer mode
* Smarter AI
* Animations and visual effects
* Game save/load system

---

## 📌 Author

Personal project built to practice Java and object-oriented programming.

---
