try:
    import cv2 as _cv2  # Optional: may be unavailable in minimal build
except Exception:
    _cv2 = None

try:
    import numpy as _np  # Optional: may be unavailable in minimal build
except Exception:
    _np = None

def analyze_tool_image(image_path: str) -> dict:
    """
    Анализирует изображение фрезы и возвращает характеристики.
    Для MVP — заглушка с тестовыми данными.
    """
    # Если OpenCV отсутствует, возвращаем заглушку без попыток чтения изображения
    if _cv2 is None:
        return {
            "tool_type": "end_mill",
            "diameter_mm": 6.0,
            "material": "HSS",
            "confidence": 0.5,
            "recommended_rpm": 12000,
            "feed_rate_mm_min": 200,
            "opencv_available": False
        }

    # Пример безопасного чтения изображения, если OpenCV доступен
    img = _cv2.imread(image_path, _cv2.IMREAD_COLOR)
    if img is None:
        # Файл не найден или не читается — возвращаем базовые значения
        return {
            "tool_type": "unknown",
            "diameter_mm": 0.0,
            "material": "unknown",
            "confidence": 0.0,
            "recommended_rpm": 0,
            "feed_rate_mm_min": 0,
            "opencv_available": True
        }

    # TODO: Здесь будет ваш AI/обработка изображения
    # Пока возвращаем тестовые данные с отметкой, что OpenCV доступен
    return {
        "tool_type": "end_mill",
        "diameter_mm": 6.0,
        "material": "HSS",
        "confidence": 0.92,
        "recommended_rpm": 18000,
        "feed_rate_mm_min": 300,
        "opencv_available": True
    }

def test_python_environment() -> str:
    """Тестовая функция для проверки работы Python и доступности опциональных библиотек"""
    opencv_ver = _cv2.__version__ if _cv2 is not None else "not installed"
    numpy_ver = _np.__version__ if _np is not None else "not installed"
    return f"Python OK | OpenCV: {opencv_ver} | NumPy: {numpy_ver}"