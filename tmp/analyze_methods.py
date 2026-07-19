from __future__ import annotations

import itertools
import re
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
YEARS = {str(y) for y in range(2016, 2027)}


def candidates():
    for path in ROOT.rglob("*"):
        if not path.is_file() or path.parts[-1] in {".DS_Store"}:
            continue
        rel = path.relative_to(ROOT)
        if not rel.parts or rel.parts[0] not in YEARS:
            continue
        name = path.name.lower().strip()
        if path.suffix.lower() in {".pdf", ".docx"}:
            continue
        if not ("q1" in name or "q2" in name or name == "minpoints.java"):
            continue
        try:
            text = path.read_text(errors="ignore")
        except OSError:
            continue
        if text.strip():
            yield rel, text


def strip_comments(text: str) -> str:
    text = re.sub(r"/\*.*?\*/", " ", text, flags=re.S)
    text = re.sub(r"//.*", " ", text)
    return text


def tokens(text: str) -> list[str]:
    text = strip_comments(text)
    return re.findall(r"[A-Za-z_$][A-Za-z0-9_$]*|\d+|==|!=|<=|>=|&&|\|\||\+\+|--|[-+*/%<>{}()[\],.;?:=]", text)


def shingles(text: str, n: int = 5) -> set[tuple[str, ...]]:
    toks = tokens(text)
    return {tuple(toks[i : i + n]) for i in range(max(0, len(toks) - n + 1))}


rows = list(candidates())
method_groups: dict[str, list[Path]] = defaultdict(list)
for path, text in rows:
    clean = strip_comments(text)
    for name in re.findall(r"\bpublic\s+static\s+(?:[\w$<>\[\]]+\s+)+([A-Za-z_$][\w$]*)\s*\(", clean):
        method_groups[name].append(path)

print("=== PUBLIC METHOD NAMES REPEATED ACROSS YEARS ===")
for method, paths in sorted(method_groups.items()):
    years = sorted({str(p).split("/")[0] for p in paths})
    if len(years) >= 2:
        print(f"{method}: years={','.join(years)}")
        for p in paths:
            print(f"  {p}")

print("\n=== HIGH TOKEN-SHINGLE SIMILARITY ACROSS YEARS ===")
sh = {p: shingles(t) for p, t in rows}
similar = []
for (p1, _), (p2, _) in itertools.combinations(rows, 2):
    y1, y2 = str(p1).split("/")[0], str(p2).split("/")[0]
    if y1 == y2 or not sh[p1] or not sh[p2]:
        continue
    score = len(sh[p1] & sh[p2]) / len(sh[p1] | sh[p2])
    containment = len(sh[p1] & sh[p2]) / min(len(sh[p1]), len(sh[p2]))
    if score >= 0.20 or containment >= 0.45:
        similar.append((score, containment, p1, p2))
for score, containment, p1, p2 in sorted(similar, reverse=True):
    print(f"jaccard={score:.3f} containment={containment:.3f} :: {p1} <> {p2}")
