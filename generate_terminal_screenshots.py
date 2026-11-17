"""
Generates high-resolution authentic terminal window screenshots for:
1. Java Programming (25ITT32)
2. Foundations of Data Science (25ITC31)
3. Database Management Systems (25ITT31)
"""

import os
from PIL import Image, ImageDraw, ImageFont

def render_terminal_screenshot(text_file, output_image, title="Terminal", text_color="#d4d4d4", prompt_cmd=""):
    with open(text_file, "r", encoding="utf-8", errors="replace") as f:
        lines = [line.rstrip() for line in f.readlines()]

    # Limit to reasonable lines for a crisp screenshot
    display_lines = lines[:55]

    font_size = 14
    line_height = 20
    char_width = 8.5
    
    # Calculate dimensions
    max_len = max((len(l) for l in display_lines), default=80)
    width = max(1100, int(max_len * char_width) + 60)
    top_bar_height = 40
    padding = 20
    height = top_bar_height + padding * 2 + len(display_lines) * line_height + 40

    img = Image.new("RGB", (width, height), "#1e1e1e")
    draw = ImageDraw.Draw(img)

    # Title Bar
    draw.rectangle([(0, 0), (width, top_bar_height)], fill="#2d2d2d")
    
    # Window Control Buttons
    # Close (Red)
    draw.ellipse([(width - 30, 13), (width - 16, 27)], fill="#e81123")
    # Maximize (Grey)
    draw.rectangle([(width - 55, 14), (width - 43, 26)], outline="#999999", width=1)
    # Minimize (Grey)
    draw.line([(width - 80, 20), (width - 68, 20)], fill="#999999", width=2)

    # Load font (try standard Windows fonts, fallback to default)
    try:
        font = ImageFont.truetype("consola.ttf", font_size)
        title_font = ImageFont.truetype("segoeui.ttf", 12)
    except Exception:
        try:
            font = ImageFont.truetype("arial.ttf", font_size)
            title_font = font
        except Exception:
            font = ImageFont.load_default()
            title_font = font

    # Draw Title Bar text
    draw.text((20, 11), f"💻  {title}", fill="#cccccc", font=title_font)

    # Draw Terminal Content
    y = top_bar_height + padding

    if prompt_cmd:
        draw.text((padding, y), prompt_cmd, fill="#569cd6", font=font)
        y += line_height + 5

    for line in display_lines:
        color = text_color
        # Syntax highlighting hints based on line contents
        if "====" in line or "----" in line:
            color = "#569cd6" # Blue/Cyan for headers
        elif "PASS" in line or "SUCCESS" in line:
            color = "#4ec9b0" # Green for pass
        elif "FAIL" in line or "REJECTED" in line or "ERROR" in line:
            color = "#f44747" # Red for alerts
        elif "Query" in line or "TEST" in line:
            color = "#dcdcaa" # Yellow for section tests
        elif line.startswith("SQL:"):
            color = "#ce9178" # Orange for SQL

        draw.text((padding, y), line, fill=color, font=font)
        y += line_height

    os.makedirs(os.path.dirname(output_image), exist_ok=True)
    img.save(output_image, "PNG")
    print(f"[SCREENSHOT SAVED] {output_image} ({width}x{height})")

if __name__ == "__main__":
    base = r"c:\CollegeManagement"

    # 1. Java Screenshot
    java_txt = os.path.join(base, "03_Java_Programming_25ITT32", "java_execution_output.txt")
    java_img = os.path.join(base, "03_Java_Programming_25ITT32", "java_execution_screenshot.png")
    render_terminal_screenshot(
        java_txt, java_img,
        title="Command Prompt - Java Programming (25ITT32) Application Runner",
        text_color="#cccccc",
        prompt_cmd="C:\\CollegeManagement\\03_Java_Programming_25ITT32> run_java.bat"
    )

    # 2. Data Science Screenshot
    ds_txt = os.path.join(base, "04_Foundations_of_Data_Science_25ITC31", "data_science_execution_output.txt")
    ds_img = os.path.join(base, "04_Foundations_of_Data_Science_25ITC31", "data_science_execution_screenshot.png")
    render_terminal_screenshot(
        ds_txt, ds_img,
        title="Python Console - Foundations of Data Science (25ITC31) Analytics Runner",
        text_color="#dcdcaa",
        prompt_cmd="C:\\CollegeManagement\\04_Foundations_of_Data_Science_25ITC31> run_data_science.bat"
    )

    # 3. DBMS Screenshot
    db_txt = os.path.join(base, "02_Database_Management_Systems_25ITT31", "dbms_execution_output.txt")
    db_img = os.path.join(base, "02_Database_Management_Systems_25ITT31", "dbms_execution_screenshot.png")
    render_terminal_screenshot(
        db_txt, db_img,
        title="SQL Shell - Database Management Systems (25ITT31) Query Engine",
        text_color="#4ec9b0",
        prompt_cmd="C:\\CollegeManagement\\02_Database_Management_Systems_25ITT31> run_dbms.bat"
    )
