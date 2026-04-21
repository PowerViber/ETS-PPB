```markdown
# Design System Strategy: The Culinary Editorial

## 1. Overview & Creative North Star
The Creative North Star for this design system is **"The Spiced Editorial."** 

We are moving away from the "utility-first" look of standard recipe apps and toward the feeling of a premium, high-end food journal. This design system treats every recipe as a cover story. By leveraging a sophisticated "Nusantara" palette—inspired by the vibrant reds of sambal, the deep greens of banana leaves, and the warm earths of galangal—we create a sensory experience that evokes appetite before a single word is read.

The layout rejects the rigid, boxed-in grid of traditional apps. Instead, we utilize **intentional asymmetry, overlapping elements, and extreme typographic scale** to create a rhythmic flow. This isn't just an interface; it’s a digital curation of culture.

---

## 2. Colors: Tonal Depth & Warmth
The palette is rooted in organic warmth. We use high-chroma accents to guide the eye and soft, paper-like neutrals to provide breathing room.

### The "No-Line" Rule
**Explicit Instruction:** Designers are prohibited from using 1px solid borders to section content. Boundaries must be defined solely through background color shifts or subtle tonal transitions. For example, a recipe ingredient list should sit on a `surface-container-low` section against a `surface` background, creating a natural, soft-edged separation.

### Surface Hierarchy & Nesting
Treat the UI as a series of physical layers—like stacked sheets of fine handmade paper.
*   **Base:** `surface` (#fbf9f5) serves as our canvas.
*   **Nesting:** Use `surface-container-low` for large section backgrounds and `surface-container-highest` for prominent interactive cards. This creates "nested depth" where the eye perceives importance through color weight rather than structural lines.

### The "Glass & Gradient" Rule
To elevate the "out-of-the-box" Material feel, use **Glassmorphism** for floating elements (e.g., a "Save Recipe" button overlaying a hero image). Use semi-transparent surface colors with a `backdrop-blur` of 12px–20px. 
*   **Signature Gradients:** For main CTAs, use a subtle linear gradient from `primary` (#ad2c00) to `primary-container` (#d34011) at a 135-degree angle. This adds a "soulful" glow that flat colors lack.

---

## 3. Typography: The Heritage Contrast
The typography system relies on the tension between the traditional and the modern.

*   **The Voice (Noto Serif):** Used for `display` and `headline` tiers. This font carries the "Editorial" weight. It should feel expensive and authoritative. Use `display-lg` for recipe titles to create a high-fashion magazine aesthetic.
*   **The Utility (Manrope):** Used for `title`, `body`, and `label` tiers. This is a clean, technical sans-serif that ensures cooking instructions remain legible even in a steamy kitchen.
*   **Hierarchy:** Always pair a `headline-lg` (Serif) with a `label-md` (Sans-Serif, All Caps, tracked out +10%) to create a sophisticated, curated contrast.

---

## 4. Elevation & Depth
We convey hierarchy through **Tonal Layering** rather than traditional drop shadows.

*   **The Layering Principle:** Stacking `surface-container` tiers is the primary way to show lift. A `surface-container-lowest` card placed on a `surface-container-low` background creates a soft, natural lift.
*   **Ambient Shadows:** If a floating effect is required for a floating action button (FAB), shadows must be extra-diffused (Blur: 24px+) and low-opacity (4%-6%). The shadow color must be tinted with the `on-surface` color (#1b1c1a) to mimic natural ambient light.
*   **The "Ghost Border" Fallback:** If a container requires a border for accessibility, use the `outline-variant` token at 15% opacity. Never use 100% opaque borders.
*   **Backdrop Blur:** Use blurs to soften the edges of the UI, making the food photography bleed into the interface for a more immersive, "non-cluttered" feel.

---

## 5. Components

### Cards & Lists
*   **Rule:** Forbid the use of divider lines.
*   **Styling:** Recipe cards should use the `xl` (1.5rem) roundedness scale. Use `surface-container-low` for the card body. The image should be the "hero," often breaking the card's boundary slightly with an asymmetrical crop or an overlapping title.

### Buttons
*   **Primary:** Use the `primary` to `primary-container` gradient. Roundedness: `full` (9999px) for a friendly, modern touch.
*   **Secondary:** No fill. Use a "Ghost Border" (`outline-variant` at 20%) with `on-surface` text.
*   **Tertiary:** Text only, using the `secondary` (#1b6d24) green to denote "freshness" and secondary actions.

### Ingredient Chips
*   **Style:** Use `surface-container-high` with `label-md` text. These should feel like small, tactile pebbles. Roundedness: `md`.

### Input Fields
*   **Style:** Minimalist. No bottom line. Use a `surface-container-highest` background with a `sm` (0.25rem) corner radius. The label should be `label-md` in `on-surface-variant`.

### Floating Navigation
*   **Style:** Instead of a docked bottom bar, use a floating navigation pill. Apply Glassmorphism (semi-transparent `surface` + backdrop blur) and an ambient shadow to make it feel like it's hovering over the content.

---

## 6. Do's and Don'ts

### Do:
*   **Prioritize Negative Space:** If a screen feels "busy," remove a container background rather than adding a line.
*   **Asymmetrical Compositions:** Place text slightly off-center or let images bleed off the edge of the screen to create a dynamic, editorial feel.
*   **Color as Instruction:** Use the `secondary` (#1b6d24) green exclusively for "Freshness" cues (e.g., prep time, health tags).

### Don't:
*   **Don't use pure black:** Use `on-surface` (#1b1c1a) for text to maintain the "earthy" warmth.
*   **Don't use standard shadows:** Avoid the default Material Design "Elevation 1-5" shadows. Stick to Tonal Layering.
*   **Don't crowd the photography:** Every recipe image needs "breathing room." Ensure the `surface` color surrounds the image to let the colors of the food pop.
*   **Don't use dividers:** Never use a horizontal line to separate two list items. Use 16px or 24px of vertical white space instead.

---

## 7. Spacing & Rhythm
This design system relies on a strict 8pt grid but encourages "broken" placements.
*   **Hero Padding:** 24px (`xl` roundedness equivalent).
*   **Internal Card Padding:** 16px.
*   **Section Gaps:** Use 40px–48px between major sections to emphasize the "Editorial" non-cluttered style. High-end design is defined by what you leave out.