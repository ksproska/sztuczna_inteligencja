from datetime import datetime
from input_classes import BookHandler, Book
from input_methods import get_books_with_any_genres_and_description


splitted_lines = get_books_with_any_genres_and_description("booksummaries/booksummaries.txt")
book_handler = BookHandler()
for line in splitted_lines:
    next_book = Book(*line)
    book_handler.add_book(next_book)

book_handler.remove_genre("Fiction")
book_handler.remove_genre("Speculative fiction")
book_handler.remove_genre("Novel")
book_handler.remove_genre("Novella")
book_handler.remove_genre("Short story")
book_handler.remove_genre("Non-fiction")
book_handler.remove_genre("Alternate history")
book_handler.remove_genre("Historical fiction")
book_handler.remove_genre("Historical novel")
book_handler.remove_genre("War novel")
book_handler.remove_genre("Suspense")
book_handler.remove_genre("Mystery")
book_handler.remove_genre("Gothic fiction")
# book_handler.remove_genre("Young adult literature")

book_handler.merge_synonims("Autobiography", "Autobiographical novel")
book_handler.merge_synonims("Crime Fiction", "Detective fiction")
book_handler.merge_synonims("Dystopia", "Utopian and dystopian fiction")
book_handler.merge_synonims("Biography", "Autobiography")
book_handler.merge_synonims("Biography", "Memoir")
book_handler.merge_synonims("Humour", "Black comedy")
book_handler.merge_synonims("Romance novel", "Paranormal romance")
# book_handler.merge_synonims("Historical fiction", "Historical novel")
# book_handler.merge_synonims("Fantasy", "High fantasy")
# book_handler.merge_synonims("Fantasy", "Dark fantasy")
# book_handler.merge_synonims("Fantasy", "Urban fantasy")
# book_handler.merge_synonims("Science Fiction", "Hard science fiction")
# book_handler.merge_synonims("Science Fiction", "Military science fiction")
# book_handler.merge_synonims("Thriller", "Techno-thriller")

book_handler.remove_books_with_all_genres('Science Fiction', 'Fantasy')
book_handler.remove_books_with_all_genres("Children's literature", 'Horror')
book_handler.remove_books_with_all_genres("Children's literature", 'Thriller')
book_handler.remove_books_with_all_genres("Children's literature", 'Young adult literature')
# book_handler.merge_synonims("Comedy", "Humor")

book_handler.remove_genre_if_count(lambda x: x < 300)
# book_handler.remove_genre_if_count(lambda x: x > 1000)

print(book_handler)
book_handler.write_to_csv(f"normalised_genres_{str(datetime.now()).replace(':', '-')}.csv")
