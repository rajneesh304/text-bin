"use client";

import CustomButton from './CustomButton'

const Hero = () => {
  const handleScroll = () => { }

  return (
    <div className='hero'>
      <div className='flex-1 pt-36 padding-x'>
        <h1 className='hero__title'>
          Keep,track,share a text block
        </h1>

        <p className='hero__subtitle'>
          Streamline managing your text as per your need.
        </p>

        <CustomButton
          title="Bin Now!"
          containerStyles="bg-primary-blue text-white rounded-full mt-10"
          handleClick={handleScroll}
        />
      </div>
    </div>
  )
}

export default Hero