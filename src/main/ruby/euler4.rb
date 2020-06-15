def isPalindrome(n)
  return n.to_s == n.to_s.reverse!
end

def largestPalindrome
  palindromes = {}

  for x in 100...1000
    for y in 100...1000
      prod = x * y
      palindromes[prod] = [x, y] if isPalindrome prod
    end
  end

  biggest = palindromes.keys.sort.last
  [biggest, palindromes[biggest]]
end

biggest = largestPalindrome
puts "Largest palindrome that is the product of two 3-digit numbers: #{biggest[1][0]} * #{biggest[1][1]} = #{biggest[0]}"
